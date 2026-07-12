package namidevelopment.kiriyaga.nami.impl.feature.visuals;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;
import net.minecraft.client.CameraType;

import static namidevelopment.kiriyaga.api.NamiApi.MC;

@RegisterFeature
public class FreeLookFeature extends Feature { // todo this shit broke
    public float cameraYaw;
    public float cameraPitch;

    private CameraType previousPerspective;

    public DoubleSetting sensivity = addSetting(new DoubleSetting("Sensivity", "灵敏度", 5, 2, 15));


    public FreeLookFeature() {
        super("FreeLook", "自由视角", "自由环视而不改变真实偏航角/俯仰角。", FeatureCategory.of("Render"), "freelook", "freelok", "third");
    }

    @Override
    public void onEnable() {
        if (MC.player == null || MC.level == null) {
            toggle();
            return;
        }

        cameraYaw = MC.player.getYRot();
        cameraPitch = MC.player.getXRot();

        previousPerspective = MC.options.getCameraType();
        if (previousPerspective != CameraType.THIRD_PERSON_BACK) {
            MC.options.setCameraType(CameraType.THIRD_PERSON_BACK);
        }
    }

    @Override
    public void onDisable() {
        if (previousPerspective != null && MC.options.getCameraType() != previousPerspective) {
            MC.options.setCameraType(previousPerspective);
        }
        if (previousPerspective == null) {
            MC.options.setCameraType(CameraType.FIRST_PERSON);
        }
    }
}
