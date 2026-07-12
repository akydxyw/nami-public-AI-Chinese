package namidevelopment.kiriyaga.nami.impl.feature.visuals;

import namidevelopment.kiriyaga.api.annotation.SubscribeEvent;
import namidevelopment.kiriyaga.api.event.impl.Render3DEvent;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.nami.impl.feature.client.ColorFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.util.render.RenderUtil;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import static namidevelopment.kiriyaga.api.NamiApi.MC;
import static namidevelopment.kiriyaga.api.NamiApi.FEATURE_SERVICE;

@RegisterFeature
public class BlockHighlightFeature extends Feature {

    public final BoolSetting fill = addSetting(new BoolSetting("Fill", "填充", true));

    public BlockHighlightFeature() {
        super("BlockHighlight", "方块高亮", "高亮你注视的方块。", FeatureCategory.of("Render"));
    }

    @SubscribeEvent
    public void onRender3dEvent(Render3DEvent event) {
        if (MC.hitResult != null && MC.hitResult instanceof BlockHitResult blockHitResult) {
            if (blockHitResult.getType() == HitResult.Type.MISS || blockHitResult.getType() == HitResult.Type.ENTITY)
                return;

            RenderUtil.drawBlockPosLines(MC.level, blockHitResult.getBlockPos(), MC.level.getBlockState(blockHitResult.getBlockPos()), FEATURE_SERVICE.getStorage().getByClass(ColorFeature.class).getStyledGlobalColor(), fill.get(), true, 1.5f);
        }
    }
}
