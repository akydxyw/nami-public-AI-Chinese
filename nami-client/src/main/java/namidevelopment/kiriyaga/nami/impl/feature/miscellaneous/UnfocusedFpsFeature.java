package namidevelopment.kiriyaga.nami.impl.feature.miscellaneous;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;

@RegisterFeature
public class UnfocusedFpsFeature extends Feature {

    public final IntSetting limit = addSetting(new IntSetting("Limit", "限制", 15, 15, 30));

    public UnfocusedFpsFeature() {
        super("UnfocusedFPS", "后台帧率", "在未聚焦时限制帧生成。", FeatureCategory.of("Miscellaneous"), "unfocusedcpu");
    }
}
