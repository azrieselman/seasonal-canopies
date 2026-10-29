package dev.tyty.seasonalcanopies.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.state.BlockState;

public class FallingLeafParticle extends TextureSheetParticle {
    private final float spin;

    protected FallingLeafParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.pickSprite(sprites);
        this.gravity = 0.035F;
        this.friction = 0.96F;
        this.lifetime = 70 + this.random.nextInt(40);
        this.quadSize = 0.11F + this.random.nextFloat() * 0.07F;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.hasPhysics = true;
        this.roll = this.random.nextFloat() * ((float) Math.PI * 2.0F);
        this.oRoll = this.roll;
        this.spin = (this.random.nextFloat() - 0.5F) * 0.12F;
        this.applyLeafColor(x, y, z);
    }

    private void applyLeafColor(double x, double y, double z) {
        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState state = this.level.getBlockState(pos);
        int color = Minecraft.getInstance().getBlockColors().getColor(state, this.level, pos, 0);
        if (color == -1) {
            color = 0x6B8F3C;
        }
        this.setColor(((color >> 16) & 255) / 255.0F, ((color >> 8) & 255) / 255.0F, (color & 255) / 255.0F);
    }

    @Override
    public void tick() {
        this.oRoll = this.roll;
        this.roll += this.spin;
        if (this.age > this.lifetime - 16) {
            this.alpha = Math.max(0.0F, (this.lifetime - this.age) / 16.0F);
        }
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new FallingLeafParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
