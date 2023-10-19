package net.pixeldream.valormobs.screen;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.util.Identifier;
import net.pixeldream.valormobs.ValorMobs;

public class SkullhavenScreen extends BaseUIModelScreen<FlowLayout> {

    public SkullhavenScreen() {
        super(FlowLayout.class, DataSource.asset(new Identifier(ValorMobs.MOD_ID, "skullhaven_ui_model")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.childById(ButtonComponent.class, "next-button").onPress(button -> this.close());
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}
