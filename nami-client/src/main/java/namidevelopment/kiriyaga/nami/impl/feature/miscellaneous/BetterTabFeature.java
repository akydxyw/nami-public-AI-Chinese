package namidevelopment.kiriyaga.nami.impl.feature.miscellaneous;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;

@RegisterFeature
public class BetterTabFeature extends Feature {

    public final IntSetting limit = addSetting(new IntSetting("Limit", "限制", 300, 25, 2500));
    public final DoubleSetting scale = addSetting(new DoubleSetting("Scale", "缩放", 1.00, 0.50, 1.50));
    //public final IntSetting columns = addSetting(new IntSetting("columns", 4, 1, 20));
    //public final IntSetting rows = addSetting(new IntSetting("rows", 5, 1, 20));
    public final BoolSetting socialsOnly = addSetting(new BoolSetting("OnlyFriends", "仅好友", false));
    public final BoolSetting highlight = addSetting(new BoolSetting("Highlight", "高亮", true));

    public BetterTabFeature() {
        super("BetterTab", "列表增强", "扩展列表限制并进行调整。", FeatureCategory.of("Miscellaneous"), "bettertab");
    }
}
