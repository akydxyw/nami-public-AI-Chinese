package namidevelopment.kiriyaga.nami.impl.feature.client;

import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.BoolSetting;
import namidevelopment.kiriyaga.api.model.setting.DoubleSetting;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;

import static namidevelopment.kiriyaga.nami.Nami.*;
import static namidevelopment.kiriyaga.api.NamiApi.*;
@RegisterFeature
public class ClickGuiFeature extends Feature {

    public final DoubleSetting scale = addSetting(new DoubleSetting("Scale", "缩放", 1.00, 0.50, 1.50));
    public final BoolSetting lines = addSetting(new BoolSetting("Lines", "线条", true));
    //public final BoolSetting descriptions = addSetting(new BoolSetting("Descriptions", true));
    public final IntSetting guiAlpha = addSetting(new IntSetting("UIAlpha", "界面透明度", 70, 0, 255));
    public final BoolSetting blur = addSetting(new BoolSetting("Blur", "模糊", false));
    public final BoolSetting background = addSetting(new BoolSetting("Background", "背景", true));
    public final BoolSetting gradientFill = addSetting(new BoolSetting("Gradient", "渐变", true));
    public final BoolSetting gear = addSetting(new BoolSetting("Gear", "齿轮", true));

    public ClickGuiFeature() {
        super("ClickGui", "点击界面", "打开客户端界面。", FeatureCategory.of("Client"), "clickgui","click", "gui", "menu", "clckgui");
        this.keyBind.set(80);
    }

    @Override
    public void onEnable(){
        if (MC == null || MC.mouseHandler == null)
            return;

        NAVIGATE_PANEL.resetActive();
        CLICK_GUI_SCREEN.scale = this.scale.get().floatValue(); // bad
        HUD_EDITOR_SCREEN.scale = this.scale.get().floatValue(); // bad
        //CLICK_GUI_SCREEN.setPreviousScreen(MC.screen);

        CLICK_GUI_SCREEN.refreshPanels();
        HUD_EDITOR_SCREEN.refreshPanels();

        MC.setScreen(CLICK_GUI_SCREEN);
        this.toggle();
    }
}
