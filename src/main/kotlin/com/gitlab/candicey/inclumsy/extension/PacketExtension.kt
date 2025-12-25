package com.gitlab.candicey.inclumsy.extension

import com.gitlab.candicey.inclumsy.delayedPacketList
import com.gitlab.candicey.inclumsy.packet.DelayedPacket
import net.minecraft.network.Packet
import net.minecraft.network.play.server.*
import com.gitlab.candicey.inclumsy.config.enabled

fun Packet<*>.spoof(): Boolean =
    if (enabled && isPlayClientPacket()) {
        delayedPacketList.add(DelayedPacket(this))
        true
    } else {
        false
    }

fun Packet<*>.isPlayClientPacket(): Boolean =
    this is S0EPacketSpawnObject
            || this is S11PacketSpawnExperienceOrb
            || this is S2CPacketSpawnGlobalEntity
            || this is S0FPacketSpawnMob
            || this is S3BPacketScoreboardObjective
            || this is S10PacketSpawnPainting
            || this is S0CPacketSpawnPlayer
            || this is S0BPacketAnimation
            || this is S37PacketStatistics
            || this is S25PacketBlockBreakAnim
            || this is S36PacketSignEditorOpen
            || this is S35PacketUpdateTileEntity
            || this is S24PacketBlockAction
            || this is S23PacketBlockChange
            || this is S02PacketChat
            || this is S3APacketTabComplete
            || this is S22PacketMultiBlockChange
            || this is S34PacketMaps
            || this is S32PacketConfirmTransaction
            || this is S2EPacketCloseWindow
            || this is S30PacketWindowItems
            || this is S2DPacketOpenWindow
            || this is S31PacketWindowProperty
            || this is S2FPacketSetSlot
            || this is S3FPacketCustomPayload
            || this is S0APacketUseBed
            || this is S19PacketEntityStatus
            || this is S1BPacketEntityAttach
            || this is S27PacketExplosion
            || this is S2BPacketChangeGameState
            || this is S00PacketKeepAlive
            || this is S21PacketChunkData
            || this is S26PacketMapChunkBulk
            || this is S14PacketEntity
            || this is S08PacketPlayerPosLook
            || this is S2APacketParticles
            || this is S39PacketPlayerAbilities
            || this is S38PacketPlayerListItem
            || this is S13PacketDestroyEntities
            || this is S1EPacketRemoveEntityEffect
            || this is S07PacketRespawn
            || this is S19PacketEntityHeadLook
            || this is S09PacketHeldItemChange
            || this is S3DPacketDisplayScoreboard
            || this is S1CPacketEntityMetadata
            || this is S12PacketEntityVelocity
            || this is S04PacketEntityEquipment
            || this is S1FPacketSetExperience
            || this is S06PacketUpdateHealth
            || this is S3EPacketTeams
            || this is S3CPacketUpdateScore
            || this is S05PacketSpawnPosition
            || this is S03PacketTimeUpdate
            || this is S33PacketUpdateSign
            || this is S29PacketSoundEffect
            || this is S0DPacketCollectItem
            || this is S18PacketEntityTeleport
            || this is S20PacketEntityProperties
            || this is S1DPacketEntityEffect
            || this is S42PacketCombatEvent
            || this is S41PacketServerDifficulty
            || this is S43PacketCamera
            || this is S44PacketWorldBorder
            || this is S45PacketTitle
            || this is S46PacketSetCompressionLevel
            || this is S47PacketPlayerListHeaderFooter
            || this is S48PacketResourcePackSend
            || this is S49PacketUpdateEntityNBT