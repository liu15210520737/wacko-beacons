package net.scoobis.mixin.client;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
	// 1.21.4 (Yarn): EFFECTS_BY_LEVEL -> 26.2 (Mojang 官方名): BEACON_EFFECTS
	// Mixin 会把本类的静态初始化合并到目标类的 <clinit> 末尾，
	// 从而在客户端把「生命恢复」加入信标第二层级可选效果。
	@Shadow
	public static final List<List<Holder<MobEffect>>> BEACON_EFFECTS = List.of(
			List.of(MobEffects.SPEED, MobEffects.HASTE),
			List.of(MobEffects.REGENERATION, MobEffects.RESISTANCE),
			List.of(MobEffects.JUMP_BOOST, MobEffects.STRENGTH),
			List.of()
	);
}
