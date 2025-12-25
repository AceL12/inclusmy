package com.gitlab.candicey.inclumsy.hook

import com.gitlab.candicey.inclumsy.extension.spoof
import com.gitlab.candicey.zenithcore.util.weave.internalNameOf
import com.gitlab.candicey.zenithcore.util.weave.named
import net.minecraft.network.Packet
import net.weavemc.api.Hook
import net.weavemc.internals.asm
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LabelNode

/**
 * @see [net.minecraft.network.NetworkManager.channelRead0]
 */
object NetworkManagerHook : Hook("net/minecraft/network/NetworkManager") {
    override fun transform(node: ClassNode, cfg: AssemblerConfig) {
        node.methods.named("channelRead0").instructions.insert(asm {
            val labelNode = LabelNode()
            aload(2)
            invokestatic(internalNameOf<NetworkManagerHook>(), "onChannelRead0", "(Lnet/minecraft/network/Packet;)Z")
            ifeq(labelNode)
            _return
            +labelNode
        })

        cfg.computeFrames()
    }

    @JvmStatic
    fun onChannelRead0(packet: Packet<*>): Boolean = packet.spoof()
}