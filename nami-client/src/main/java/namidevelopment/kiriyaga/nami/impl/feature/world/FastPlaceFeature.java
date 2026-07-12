package namidevelopment.kiriyaga.nami.impl.feature.world;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;
import namidevelopment.kiriyaga.api.model.setting.WhitelistSetting;

@RegisterFeature
public class FastPlaceFeature extends Feature {

    public final IntSetting delay = addSetting(new IntSetting("Delay", "延迟", 1, 0, 5));
    public final IntSetting startDelay = addSetting(new IntSetting("StartDelay", "起始延迟", 10, 0, 50));
    public final WhitelistSetting whitelist = addSetting(new WhitelistSetting("WhiteList", "白名单", false, WhitelistSetting.Type.ANY));
    public final WhitelistSetting blacklist = addSetting(new WhitelistSetting("BlackList", "黑名单", false, WhitelistSetting.Type.ANY));

    public FastPlaceFeature() {
        super("FastPlace", "快速放置", "减少任何类型使用的冷却时间。", FeatureCategory.of("World"), "fastplace");
    }
}
