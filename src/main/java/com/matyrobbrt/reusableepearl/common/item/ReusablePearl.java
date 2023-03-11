package com.matyrobbrt.reusableepearl.common.item;

import com.matyrobbrt.reusableepearl.core.config.PearlConfig;
import com.matyrobbrt.reusableepearl.core.init.KeybindsInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class ReusablePearl extends Item {
	public final PearlConfig config;

	public ReusablePearl(Properties properties, PearlConfig config) {
		super(properties.durability(config.uses));
		this.config = config;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		final var item = player.getItemInHand(hand);
		if (!worldIn.isClientSide) {
			teleport(player, item, hand);
		}
		return InteractionResultHolder.sidedSuccess(item, worldIn.isClientSide);
	}

	public void teleport(Player player, ItemStack stack, @Nullable InteractionHand hand) {
		final ThrownEnderpearl entity = new ThrownEnderpearl(player.level, player);
		entity.setOwner(player);
		entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
		player.level.addFreshEntity(entity);

		stack.hurtAndBreak(1, player, player1 -> {
			if (hand != null) {
				player1.broadcastBreakEvent(hand);
			}
		});

		if (config.cooldown > 0) {
			player.getCooldowns().addCooldown(this, config.cooldown);
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.UNBREAKING;
	}

	@Override
	public void appendHoverText(ItemStack p_41421_, @org.jetbrains.annotations.Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
		if (config.supportsKeybind) {
			p_41423_.add(Component.translatable("tooltip.reusableepearl.supports_keybinds", KeybindsInit.throwPearl.getKey().getDisplayName().copy().withStyle(ChatFormatting.GOLD))
					.withStyle(ChatFormatting.AQUA));
		}
	}
}
