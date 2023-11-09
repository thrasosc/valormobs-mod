package net.pixeldream.valormobs.screen.skullhaven;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.resources.ResourceLocation;
import net.pixeldream.valormobs.ValorMobs;

public class SkullHavenScreen1 extends BaseUIModelScreen<FlowLayout> {

    public SkullHavenScreen1() {
        super(FlowLayout.class, DataSource.asset(new ResourceLocation(ValorMobs.MOD_ID, "skullhaven/skullhaven_ui_1")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "next-button").onPress(button -> this.minecraft.setScreen(new SkullHavenScreen2(this)));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
