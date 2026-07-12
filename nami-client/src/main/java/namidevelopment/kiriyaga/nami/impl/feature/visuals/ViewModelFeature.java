package namidevelopment.kiriyaga.nami.impl.feature.visuals;

import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;

@RegisterFeature
public class ViewModelFeature extends Feature {

    public final BoolSetting hand = addSetting(new BoolSetting("Hand", "手部", false));
    public final BoolSetting eating = addSetting(new BoolSetting("Eating", "进食", true));
    public final DoubleSetting eatingBob = addSetting(new DoubleSetting("EatingBob", "进食晃动", 1.00, 0.00, 1.00));
    public final BoolSetting oldAnimation = addSetting(new BoolSetting("OldAnimation", "旧动画", false));
    public final DoubleSetting scale = addSetting(new DoubleSetting("Scale", "缩放", 1.0, 0.1, 2));
    public final DoubleSetting posX = addSetting(new DoubleSetting("PosX", "X位置", 0.0, -3, 3));
    public final DoubleSetting posY = addSetting(new DoubleSetting("PosY", "Y位置", 0.0, -3, 3));
    public final DoubleSetting posZ = addSetting(new DoubleSetting("PosZ", "Z位置", 0.0, -3, 3));
    public final DoubleSetting rotX = addSetting(new DoubleSetting("RotX", "X旋转", 0.0, -180.0, 180.0));
    public final DoubleSetting rotY = addSetting(new DoubleSetting("RotY", "Y旋转", 0.0, -180.0, 180.0));
    public final DoubleSetting rotZ = addSetting(new DoubleSetting("RotZ", "Z旋转", 0.0, -180.0, 180.0));

    public ViewModelFeature() {
        super("Viewmodel", "视角模型", "修改手部位置、缩放和旋转。", FeatureCategory.of("Render"), "vm", "handpos");
    eatingBob.setShowCondition(() -> eating.get());
    }
}
