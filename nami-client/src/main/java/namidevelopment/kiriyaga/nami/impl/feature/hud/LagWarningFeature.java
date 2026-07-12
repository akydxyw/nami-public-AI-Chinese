package namidevelopment.kiriyaga.nami.impl.feature.hud;

import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import net.minecraft.network.chat.Component;

import static namidevelopment.kiriyaga.nami.Nami.*;
import static namidevelopment.kiriyaga.api.NamiApi.*;
@RegisterFeature
public class LagWarningFeature extends HudElementFeature {


    public LagWarningFeature() {
        super("卡顿警告", "连接不稳定时显示卡顿警告。", 0, 0, 100, 9);
    }

    @Override
    public Component getDisplayText() {
        if (!SERVER_SERVICE.isConnectionUnstable())
            return Component.empty();

        if (MC.isLocalServer() || MC.level == null) return Component.nullToEmpty("卡顿警告:");

        double seconds = SERVER_SERVICE.getUnstableTime();
        double roundedSeconds = Math.round(seconds * 100.0) / 100.0;
        String warningText = "服务器无响应 " + String.format("%.2f", roundedSeconds) + "s";

        width = FONT_SERVICE.getWidth(warningText);
        height = FONT_SERVICE.getHeight();

        return CAT_FORMAT.format("{global}" + warningText);
    }
}
