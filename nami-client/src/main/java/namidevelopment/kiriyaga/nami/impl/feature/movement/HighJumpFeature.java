package namidevelopment.kiriyaga.nami.impl.feature.movement;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;

@RegisterFeature
public class HighJumpFeature extends Feature {

    public final DoubleSetting height = addSetting(new DoubleSetting("Height", "高度", 0.42, 0.00, 1.0));

    public HighJumpFeature() {
        super("HighJump", "高跳", "修改跳跃高度。", FeatureCategory.of("Movement"), "highjump");
    }
}
