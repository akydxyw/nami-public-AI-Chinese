package namidevelopment.kiriyaga.nami.impl.feature.world;

import namidevelopment.kiriyaga.api.event.EventPriority;
import namidevelopment.kiriyaga.api.annotation.SubscribeEvent;
import namidevelopment.kiriyaga.api.event.impl.PreTickEvent;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;

import static namidevelopment.kiriyaga.api.NamiApi.INVENTORY_SERVICE;
import static namidevelopment.kiriyaga.api.NamiApi.MC;

@RegisterFeature
public class AutoFireworkFeature extends Feature {

    public final BoolSetting deployLaunch = addSetting(new BoolSetting("DeployLaunch", "部署发射", false));
    public final BoolSetting autoDeploy = addSetting(new BoolSetting("AutoDeploy", "自动部署", false));
    public final BoolSetting autoLaunch = addSetting(new BoolSetting("AutoLaunch", "自动发射", false));
    public final DoubleSetting delaySeconds = addSetting(new DoubleSetting("Delay", "延迟", 4.5, 0.1, 25.0));
    public final IntSetting onLevel = addSetting(new IntSetting("OnLevel", "在Y坐标", -64, -64, 360));

    private int tickDelay;
    private int lastUseTick = 0;
    private boolean b;

    public AutoFireworkFeature() {
        super("AutoFirework", "自动烟花", "自动发射烟花。", FeatureCategory.of("World"), "autofirework");
        delaySeconds.setShowCondition(autoLaunch::get);
        onLevel.setShowCondition(autoLaunch::get);
    }

    @Override
    public void onEnable() {
        b = false;
        if (MC.player != null) {
            lastUseTick = MC.player.tickCount - (int) Math.round(delaySeconds.get() * 20);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private void onTick(PreTickEvent ev) {
        if (MC.level == null || MC.player == null) return;

        if (MC.getConnection() != null && autoDeploy.get() && !MC.player.isFallFlying() && !MC.player.onGround() && MC.player.tryToStartFallFlying()) {
            MC.getConnection().send(new ServerboundPlayerCommandPacket(MC.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING)); // lol they still use theese
        }

        boolean isElytra = MC.player.isFallFlying();

        if (deployLaunch.get() && !b && isElytra) useItemAnywhere(Items.FIREWORK_ROCKET);

        b = isElytra;

        if (!autoLaunch.get()) return;

        if (MC.player.tickCount < lastUseTick) {
            lastUseTick = MC.player.tickCount - tickDelay;
        }

        tickDelay = (int) Math.round(delaySeconds.get() * 20);

        if (!MC.player.isFallFlying()) return;

        if (MC.player.getY() <= onLevel.get()) return;

        if (MC.player.tickCount - lastUseTick < tickDelay) return;

        if (useItemAnywhere(Items.FIREWORK_ROCKET)) {
            lastUseTick = MC.player.tickCount;
        }
    }

    private boolean useItemAnywhere(Item item) {
        int hotbarSlot = getSlotInHotbar(item);

        if (hotbarSlot != -1) {
            INVENTORY_SERVICE.getSwapHandler().attemptSwitch(hotbarSlot, true);
            MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
            return true;
        }

        int invSlot = getSlotInInventory(item);
        if (invSlot != -1) {
            int selectedHotbarIndex = MC.player.getInventory().getSelectedSlot();
            int containerInvSlot = convertSlot(invSlot);

            INVENTORY_SERVICE.getClickHandler().swapSlot(containerInvSlot, selectedHotbarIndex);
            MC.gameMode.useItem(MC.player, InteractionHand.MAIN_HAND);
            INVENTORY_SERVICE.getClickHandler().swapSlot(containerInvSlot, selectedHotbarIndex);
            return true;
        }

        return false;
    }

    private int getSlotInHotbar(Item item) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = MC.player.getInventory().getItem(i);
            if (stack.getItem() == item) return i;
        }
        return -1;
    }

    private int getSlotInInventory(Item item) {
        for (int i = 9; i < 36; i++) {
            ItemStack stack = MC.player.getInventory().getItem(i);
            if (stack.getItem() == item) return i;
        }
        return -1;
    }

    private int convertSlot(int slot) {
        return slot < 9 ? slot + 36 : slot;
    }
}
