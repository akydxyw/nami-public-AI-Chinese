package namidevelopment.kiriyaga.nami.impl.feature.miscellaneous;


import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;

@RegisterFeature
public class NoPacketKick extends Feature {

    public NoPacketKick() {
        super("NoPacketKick", "防数据包踢出", "防止因 netty 异常而被踢出。", FeatureCategory.of("Miscellaneous"), "npacketkick", "antipacketkick");
    }
}
