package namidevelopment.kiriyaga.nami.impl.feature.miscellaneous;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;

@RegisterFeature
public class AutoReconnectFeature extends Feature {

    public final BoolSetting hardHide = addSetting(new BoolSetting("HideMenu", "隐藏菜单", false));
    public final IntSetting delay = addSetting(new IntSetting("Delay", "延迟", 5, 0, 80));

    public AutoReconnectFeature() {
        super("AutoReconnect", "自动重连", "自动重新连接到指定服务器。", FeatureCategory.of("Miscellaneous"), "autoreconnect");
    }
}
