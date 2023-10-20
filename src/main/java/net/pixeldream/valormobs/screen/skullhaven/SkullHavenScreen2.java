package net.pixeldream.valormobs.screen.skullhaven;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;
import org.jetbrains.annotations.Nullable;

public class SkullHavenScreen2 extends BaseUIModelScreen<FlowLayout> {

    private final Screen parent;

    public SkullHavenScreen2(@Nullable Screen parent) {
        super(FlowLayout.class, DataSource.asset(new Identifier(ValorMobs.MOD_ID, "skullhaven/skullhaven_ui_2")));
        this.parent = parent;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "previous-button").onPress(button -> this.client.setScreen(this.parent));
        rootComponent.childById(ButtonComponent.class, "next-button").onPress(button -> this.close());
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}