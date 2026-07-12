package namidevelopment.kiriyaga.nami.impl.feature.world;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;

@RegisterFeature
public class NoBreakDelayFeature extends Feature {

    public NoBreakDelayFeature() {
        super("NoBreakDelay", "无破坏延迟", "移除原版破坏延迟，从而提高破坏速度。", FeatureCategory.of("World"), "nobreakdelay");
    }
}
