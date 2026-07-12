package namidevelopment.kiriyaga.nami.impl.feature.visuals;

import namidevelopment.kiriyaga.api.event.EventPriority;
import namidevelopment.kiriyaga.api.annotation.SubscribeEvent;
import namidevelopment.kiriyaga.api.event.impl.ParticleEvent;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;
import net.minecraft.core.particles.ParticleTypes;

import static namidevelopment.kiriyaga.api.NamiApi.MC;

@RegisterFeature
public class NoRenderFeature extends Feature {



    public final BoolSetting noTilt = addSetting(new BoolSetting("NoTilt", "无倾斜", true));
    public final BoolSetting noBob = addSetting(new BoolSetting("NoBob", "无晃动", true));
    public final IntSetting tileEntity = addSetting(new IntSetting("TileEntity", "方块实体", 0, 0, 75));
    public final BoolSetting portalGui = addSetting(new BoolSetting("PortalGui", "传送门界面", true));
    public final BoolSetting noFire = addSetting(new BoolSetting("NoFire", "无火焰", true));
    public final BoolSetting noBackground = addSetting(new BoolSetting("NoBackground", "无背景", true));
    public final BoolSetting noTotemParticle = addSetting(new BoolSetting("NoPopParticle", "无图腾粒子", false));
    public final BoolSetting noWaterParticle = addSetting(new BoolSetting("NoWaterParticle", "无水粒子", true));
    public final BoolSetting noExplosion = addSetting(new BoolSetting("NoExplosion", "无爆炸", true));
    public final BoolSetting noBlockBreak = addSetting(new BoolSetting("NoBreakParticle", "无破坏粒子", false)); // todo this shit broke
    public final BoolSetting noLiguid = addSetting(new BoolSetting("NoLiquid", "无液体", false));
    public final BoolSetting noWall = addSetting(new BoolSetting("NoWall", "无墙壁", false));
    public final BoolSetting noVignette = addSetting(new BoolSetting("NoVignette", "无晕影", true));
    public final BoolSetting noTotem = addSetting(new BoolSetting("NoTotem", "无图腾", true));
    public final BoolSetting noBossBar = addSetting(new BoolSetting("NoBoss", "无Boss条", true));
    public final BoolSetting noPortal = addSetting(new BoolSetting("NoPortalGui", "无传送门界面", true));
    public final BoolSetting noPotIcon = addSetting(new BoolSetting("NoPotIcon", "无药水图标", true));
    public final BoolSetting noDarkness = addSetting(new BoolSetting("NoDarkness", "无黑暗", true));
    public final BoolSetting noFog = addSetting(new BoolSetting("NoFog", "无雾", true));
    public final BoolSetting noArmor = addSetting(new BoolSetting("NoArmor", "无护甲", true)); // todo this shit broke
    public final BoolSetting noNausea = addSetting(new BoolSetting("NoNausea", "无反胃", true));
    public final BoolSetting noPumpkin = addSetting(new BoolSetting("NoPumpkin", "无南瓜头", false));
    public final BoolSetting noPowderedSnow = addSetting(new BoolSetting("NoPowdered", "无细雪", false));

    public NoRenderFeature() {
        super("NoRender", "无渲染", "阻止渲染特定覆盖层/效果。", FeatureCategory.of("Render"), "norender");
        noFire.setOnChanged(this::reloadRenderer);
        noBackground.setOnChanged(this::reloadRenderer);
        noLiguid.setOnChanged(this::reloadRenderer);
        noVignette.setOnChanged(this::reloadRenderer);
        noPortal.setOnChanged(this::reloadRenderer);
        noFog.setOnChanged(this::reloadRenderer);
        noPumpkin.setOnChanged(this::reloadRenderer);
        noPowderedSnow.setOnChanged(this::reloadRenderer);
    }

    private void reloadRenderer() {
        if (MC.level != null) {
            MC.levelRenderer.allChanged();
        }
    }

    @Override
    public void onEnable() {
        reloadRenderer();
    }

    @Override
    public void onDisable() {
        reloadRenderer();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onParticle(ParticleEvent ev){
        if (MC.level == null || MC.player == null)
            return;

//        if (noExplosion.get() && (ev.getParticle() instanceof ExplosionEmitterParticle || ev.getParticle() instanceof ExplosionLargeParticle || ev.getParticle() instanceof ExplosionSmokeParticle))
//            ev.cancel();

//        if (noTotemParticle.get() && ev.getParticle() instanceof TotemParticle)
//            ev.cancel();

        if (noExplosion.get() && (ev.getParticle().getType() == ParticleTypes.EXPLOSION || ev.getParticle().getType() == ParticleTypes.EXPLOSION_EMITTER))
            ev.cancel();

        if (noTotemParticle.get() && ev.getParticle().getType() == ParticleTypes.TOTEM_OF_UNDYING)
            ev.cancel();

        if (noWaterParticle.get() && (ev.getParticle().getType() == ParticleTypes.RAIN || ev.getParticle().getType() == ParticleTypes.DRIPPING_DRIPSTONE_WATER || ev.getParticle().getType() == ParticleTypes.DRIPPING_WATER || ev.getParticle().getType() == ParticleTypes.FALLING_DRIPSTONE_WATER || ev.getParticle().getType() == ParticleTypes.FALLING_WATER))
            ev.cancel();
    }
}
