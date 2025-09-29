package org.thinkingstudio.randomtitlerework.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.server.integrated.IntegratedServer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.thinkingstudio.randomtitlerework.RTRModPlatform;
import org.thinkingstudio.randomtitlerework.RandomTitleHelper;
import org.thinkingstudio.randomtitlerework.config.RTRModConfigHelper;
import org.thinkingstudio.randomtitlerework.config.RTRModConfigs;

import java.text.SimpleDateFormat;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    RTRModConfigs randomTitleRework$config = RTRModConfigHelper.getConfig();
    private final String randomTitleRework$randomTitle = RandomTitleHelper.getRandomTitle();

    @Shadow
    private IntegratedServer server;


    @Shadow
    @Nullable
    public abstract ClientPlayNetworkHandler getNetworkHandler();

    @Shadow
    @Nullable
    public abstract ServerInfo getCurrentServerEntry();

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> ci) {
        String title = randomTitleRework$config.titleFormat;
        StringBuilder stringBuilder = new StringBuilder(SharedConstants.getGameVersion().getName());
        stringBuilder.append(" ");

        ClientPlayNetworkHandler clientPlayNetworkHandler = this.getNetworkHandler();
        if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isOpen()) {
            stringBuilder.append(" - ");
            ServerInfo serverInfo = this.getCurrentServerEntry();
            if (this.server != null && !this.server.isRemote()) {
                stringBuilder.append(I18n.translate("title.singleplayer"));
            } else if (serverInfo != null && serverInfo.isRealm()) {
                stringBuilder.append(I18n.translate("title.multiplayer.realms"));
            } else if (this.server == null && (serverInfo == null || !serverInfo.isLocal())) {
                stringBuilder.append(I18n.translate("title.multiplayer.other"));
            } else {
                stringBuilder.append(I18n.translate("title.multiplayer.lan"));
            }

        }
        String date = new SimpleDateFormat(randomTitleRework$config.dateFormat).format((System.currentTimeMillis()));
        title = title.replace("%date%", date);
        title = title.replace("%title%", randomTitleRework$randomTitle);
        title = title.replace("%version%", stringBuilder.toString());
        title = title.replace("%mod%", String.valueOf(RTRModPlatform.getModListSize()));
        ci.setReturnValue(title);
        ci.cancel();
    }
}
