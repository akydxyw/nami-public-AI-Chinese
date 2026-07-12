package namidevelopment.kiriyaga.nami.impl.feature.miscellaneous;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;

@RegisterFeature
public class NameProtectFeature extends Feature {

    public NameProtectFeature() {
        super("NameProtect", "名称保护", "在客户端可访问的所有位置更改客户端名称。", FeatureCategory.of("Miscellaneous"), "nameprotect");
    }
}
