package namidevelopment.kiriyaga.nami.impl.feature.movement;

import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;

@RegisterFeature
public class NoLevitationFeature extends Feature {

    public final BoolSetting noSlowFall = addSetting(new BoolSetting("NoSlowFall", "无缓降", false));

    public NoLevitationFeature() {
        super("NoLevitation", "无悬浮", "移除悬浮状态效果。", FeatureCategory.of("Movement"), "antilevitation");
    }
}
