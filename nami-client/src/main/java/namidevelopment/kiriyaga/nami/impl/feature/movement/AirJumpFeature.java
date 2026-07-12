package namidevelopment.kiriyaga.nami.impl.feature.movement;

import namidevelopment.kiriyaga.api.event.EventPriority;
import namidevelopment.kiriyaga.api.annotation.SubscribeEvent;
import namidevelopment.kiriyaga.api.event.impl.PreTickEvent;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.KeyBindSetting;

import static namidevelopment.kiriyaga.nami.Nami.*;
import static namidevelopment.kiriyaga.api.NamiApi.*;
@RegisterFeature
public class AirJumpFeature extends Feature {

    public final KeyBindSetting useKey = addSetting(new KeyBindSetting("Use", "使用", KeyBindSetting.KEY_NONE));
    public final BoolSetting setOnGround = addSetting(new BoolSetting("SetOnGround", "设置落地", true));

    public AirJumpFeature() {
        super("AirJump", "空中跳跃", "按键时允许在空中跳跃。", FeatureCategory.of("Movement"), "airjump");
    }

    @Override
    public void onEnable() {
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    private void onTick(PreTickEvent ev) {
        if (MC.level == null || MC.player == null) return;

        boolean pressed = KEYBIND_SERVICE.isPressedToggle(useKey);

        if (pressed) {
            performAirJump();
        }
    }

    private void performAirJump() {
        if (setOnGround.get())
            MC.player.setOnGround(true);

        MC.player.jumpFromGround();
    }
}
