package net.scoobis.mixin.client;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.BeaconScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// 1.21.4 (Yarn): BeaconScreen$EffectButtonWidget.tick(int)
// 26.2 (Mojang 官方名): BeaconScreen$BeaconPowerButton.updateStatus(int)
@Mixin(targets = "net.minecraft.client.gui.screens.inventory.BeaconScreen$BeaconPowerButton")
abstract class BeaconScreenButtonMixin extends AbstractWidget {
	public BeaconScreenButtonMixin(int x, int y, int width, int height, Component message) {
		super(x, y, width, height, message);
	}

	@Inject(at = @At("TAIL"), method = "updateStatus")
	private void wacko$forceActive(int level, CallbackInfo ci) {
		// 无论信标塔层级是否足够，始终让效果按钮可点
		this.active = true;
	}
}
