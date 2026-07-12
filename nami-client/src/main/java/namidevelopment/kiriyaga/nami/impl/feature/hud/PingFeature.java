package namidevelopment.kiriyaga.nami.impl.feature.hud;

import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import net.minecraft.network.chat.Component;

import static namidevelopment.kiriyaga.api.NamiApi.*;

@RegisterFeature
public class PingFeature extends HudElementFeature {

    public final BoolSetting displayLabel = addSetting(new BoolSetting("Label", "标签", true));

    public PingFeature() {
        super("延迟", "显示当前延迟。", 0, 0, 50, 9);
    }

    @Override
    public Component getDisplayText() {
        int ping = SERVER_SERVICE.getPing();
        String textStr;

        if (displayLabel.get()) {
            textStr = "延迟: " + ping;
        } else {
            textStr = String.valueOf(ping);
        }

        width = FONT_SERVICE.getWidth(textStr);
        height = FONT_SERVICE.getHeight();

        if (displayLabel.get()) {
            return CAT_FORMAT.format("{global}延迟: {white}" + ping);
        } else {
            return Component.literal(textStr);
        }
    }
}