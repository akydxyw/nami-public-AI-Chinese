package namidevelopment.kiriyaga.nami.impl.feature.world;

import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.annotation.SubscribeEvent;
import namidevelopment.kiriyaga.api.core.rotation.model.RotationRequest;
import namidevelopment.kiriyaga.api.event.impl.PreTickEvent;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;
import namidevelopment.kiriyaga.api.util.entity.EntityUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static namidevelopment.kiriyaga.api.NamiApi.MC;
import static namidevelopment.kiriyaga.api.NamiApi.ROTATION_SERVICE;
import static namidevelopment.kiriyaga.api.util.InteractionUtils.interactWithEntity;
import static namidevelopment.kiriyaga.api.util.RotationUtils.*;

@RegisterFeature
public class MapSpammerFeature extends Feature {

    public final BoolSetting place = addSetting(new BoolSetting("Place", "放置", true));
    public final DoubleSetting placeRange = addSetting(new DoubleSetting("PlaceRange", "范围", 4.5, 1.0, 6.0));
    public final IntSetting placeDelay = addSetting(new IntSetting("PlaceDelay", "延迟", 0, 0, 20));
    public final BoolSetting placeswapSilent = addSetting(new BoolSetting("PlaceswapSilent", "静默切换", true));
    public final BoolSetting placeMultitask = addSetting(new BoolSetting("PlaceMultitask", "多任务", false));
    public final BoolSetting placeSwing = addSetting(new BoolSetting("PlaceSwing", "挥手", true));
    public final BoolSetting placeRotate = addSetting(new BoolSetting("PlaceRotate", "旋转", true));

    public final BoolSetting attack = addSetting(new BoolSetting("Attack", "攻击", true));
    public final DoubleSetting attackRange = addSetting(new DoubleSetting("AttackRange", "范围", 3.00, 1.0, 6.0));
    public final IntSetting attackDelay = addSetting(new IntSetting("AttackDelay", "延迟", 4, 0, 20));
    public final IntSetting attackCooldownSeconds = addSetting(new IntSetting("AttackCooldown", "冷却秒数", 10, 1, 60));
    public final BoolSetting attackInhibit = addSetting(new BoolSetting("AttackInhibit", "抑制", true));
    public final BoolSetting attackRotate = addSetting(new BoolSetting("AttackRotate", "旋转", true));
    public final BoolSetting attackSwing = addSetting(new BoolSetting("AttackSwing", "挥手", true));
    public final BoolSetting attackMultitask = addSetting(new BoolSetting("AttackMultitask", "多任务", false));

    private int placeCD = 0;
    private int attackCD = 0;
    private Item referenceItem = null;
    private String referenceName = null;
    private final Set<Integer> attacked = new HashSet<>();
    private final Map<Integer, Long> attackedCD = new HashMap<>();

    public MapSpammerFeature() {
        super("MapSpammer", "地图刷屏", "自动用你的地图填充物品展示框。", FeatureCategory.of("World"));
        placeRange.setShowCondition(place::get);
        placeDelay.setShowCondition(place::get);
        placeswapSilent.setShowCondition(place::get);
        placeMultitask.setShowCondition(place::get);
        placeSwing.setShowCondition(place::get);
        placeRotate.setShowCondition(place::get);
        attackRange.setShowCondition(attack::get);
        attackDelay.setShowCondition(attack::get);
        attackCooldownSeconds.setShowCondition(attack::get);
        attackInhibit.setShowCondition(attack::get);
        attackRotate.setShowCondition(attack::get);
        attackSwing.setShowCondition(attack::get);
        attackMultitask.setShowCondition(attack::get);
    }

    @Override
    public void onEnable() {
        referenceItem = null;
        referenceName = null;
        placeCD = 0;
        attackCD = 0;
        attacked.clear();
        attackedCD.clear();
    }

    @SubscribeEvent
    public void onPreTickEvent(PreTickEvent event) {
        if (MC.player == null || MC.level == null) return;

        if (placeCD > 0) placeCD--;
        if (attackCD > 0) attackCD--;

        if (referenceItem == null) {
            findReference();
            if (referenceItem == null) return;
        }

        double scanRange = Math.max(placeRange.get(), attackRange.get()) + 8;

        for (Entity entity : EntityUtils.getEntities(EntityUtils.EntityTypeCategory.ALL, scanRange, true)) {
            if (!(entity instanceof ItemFrame frame)) continue;
            if (!frame.isAlive()) continue;

            ItemStack inFrame = frame.getItem();

            if (attack.get() && attackCD <= 0) {
                if (!inFrame.isEmpty() && !isReference(inFrame)) {
                    int id = frame.getId();
                    if (attackInhibit.get() && attacked.contains(id)) {
                        continue;
                    }
                    long now = System.currentTimeMillis();
                    long cd = attackCooldownSeconds.get() * 1000L;
                    if (attackedCD.containsKey(id)) {
                        long last = attackedCD.get(id);
                        if (now - last < cd) continue;
                    }
                    if (doBreak(frame)) {
                        attacked.add(id);
                        attackedCD.put(id, now);
                        attackCD = attackDelay.get();
                        break;
                    }
                }
            }

            if (place.get() && placeCD <= 0) {
                if (inFrame.isEmpty()) {

                    boolean success = interactWithEntity(frame, referenceItem, placeswapSilent.get(), placeMultitask.get(), placeRange.get(), placeSwing.get(), placeRotate.get(), this.name + "_place");

                    if (success) {
                        placeCD = placeDelay.get();
                        break;
                    }
                }
            }
        }
    }

    private void findReference() {
        for (int slot = 0; slot < 9; slot++) {
            ItemStack stack = MC.player.getInventory().getItem(slot);
            if (stack == null || stack.isEmpty()) continue;

            if (stack.getItem() == Items.FILLED_MAP) {
                referenceItem = stack.getItem();
                referenceName = stack.getHoverName() != null ? stack.getHoverName().getString() : null;
                return;
            }
        }
    }

    private boolean isReference(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        if (referenceItem == null) return false;

        if (stack.getItem() != referenceItem) return false;

        String name = stack.getHoverName() != null ? stack.getHoverName().getString() : null;

        if (referenceName == null && name == null) return true;
        if (referenceName == null) return false;

        return referenceName.equals(name);
    }

    private boolean doBreak(ItemFrame frame) {
        if (frame == null) return false;

        if (!attackMultitask.get() && MC.player.isUsingItem()) return false;

        Vec3 eyePos = MC.player.getEyePosition();
        if (eyePos.distanceTo(getClampClosestPoint(eyePos, frame.getBoundingBox())) > attackRange.get()) {
            return false;
        }

        boolean rotated = false;
        if (attackRotate.get()) {
            Vec3 pos = getClosestPointToEye(MC.player.getEyePosition(), frame.getBoundingBox());
            float yaw = (float) getYRotToVec(MC.player, pos);
            float pitch = (float) getXRotToVec(MC.player, pos);

            ROTATION_SERVICE.getRequestHandler().submit(new RotationRequest(this.name + "_attack", 9, yaw, pitch));

            rotated = true;
        }

        if (rotated) {
            boolean insideBox = frame.getBoundingBox().contains(MC.player.getEyePosition(1.0f));
            EntityHitResult serverCheck = raycastTarget(MC.player, frame, attackRange.get(), ROTATION_SERVICE.getStateHandler().getServerYRot(), ROTATION_SERVICE.getStateHandler().getServerXRot());

            if (serverCheck == null && !insideBox) return false;
        }
        MC.gameMode.attack(MC.player, frame);

        if (attackSwing.get()) {
            MC.player.swing(InteractionHand.MAIN_HAND);
        }

        return true;
    }
}
