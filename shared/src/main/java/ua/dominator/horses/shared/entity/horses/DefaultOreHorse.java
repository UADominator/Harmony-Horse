package ua.dominator.horses.shared.entity.horses;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import ua.dominator.horses.shared.entity.ModEntities;
import ua.dominator.horses.shared.entity.ai.HorseAttackGoal;

public class DefaultOreHorse extends Animal{
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(DefaultOreHorse.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DAMAGED = SynchedEntityData.defineId(DefaultOreHorse.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> POOP = SynchedEntityData.defineId(DefaultOreHorse.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> POOP_TIME = SynchedEntityData.defineId(DefaultOreHorse.class, EntityDataSerializers.INT);
    public int poopTime = 0;
    public final int poopProduceTime = 100;

    //Animations
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;


    public final AnimationState damagedAnimationState = new AnimationState();
    public int damagedAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public final AnimationState poopAnimationState = new AnimationState();
    public int poopAnimationTimeout = 0;


    public DefaultOreHorse(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void updatePoop(){
        poopTime++;

        if (poopTime + 10 == poopProduceTime){
            this.setPoop(true);
        }
        if (this.isPoop()){
            poopAnimationTimeout++;
        }
        if (poopAnimationTimeout >= 25){
            poopAnimationTimeout = 0;
            this.setPoop(false);
        }

        if (poopTime >= poopProduceTime){
            float rotD = this.getVisualRotationYInDegrees() + 90f;
            double rotR = Math.toRadians(rotD);
            double offsetX = - Math.cos(rotR);
            double offsetZ = - Math.sin(rotR);

            this.level().addFreshEntity(new ItemEntity(this.level(),
                    this.getX() + offsetX,
                    this.getY() + 0.5f ,
                    this.getZ() + offsetZ,
                    new ItemStack(Items.BEDROCK)));
            poopTime = 0;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setupLocals();
        if (this.level().isClientSide()){
            this.setupAnimationStates();

            return; //Stop do any when client side
        }
        updatePoop();
    }

    @Override
    public void handleDamageEvent(DamageSource damageSource) {
        super.handleDamageEvent(damageSource);
        this.setDamaged(true);
    }

    @Override
    protected void updateWalkAnimation(float pPartialTicks) {
        float f;
        if (this.getPose() == Pose.STANDING){
            f = Math.min(pPartialTicks * 6f, 1f);
        } else {
            f = 0;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    private void setupLocals(){
        if (!this.level().isClientSide) {
            this.setPoopTime(this.poopTime);
        }
        this.poopTime = this.getPoopTime();
    }

    private void setupAnimationStates(){
        //idle
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        //attack
        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 40; // Length in ticks of animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }
        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }

        //get damage
        if(this.isDamaged()) {
            if (damagedAnimationTimeout == 0){
                damagedAnimationState.start(this.tickCount);
                damagedAnimationTimeout++;
            } else if (damagedAnimationTimeout <= 20){
                damagedAnimationTimeout++;
            } else {
                damagedAnimationTimeout = 0;
                this.setDamaged(false);
                damagedAnimationState.stop();
            }
        }

        //poop
        if(this.isPoop()) {
            if (poopAnimationTimeout == 0){
                poopAnimationState.start(this.tickCount);
                poopAnimationTimeout++;
            } else if (poopAnimationTimeout <= 20){
                poopAnimationTimeout++;
            } else {
                poopAnimationTimeout = 0;
                this.setPoop(false);
                poopAnimationState.stop();
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DAMAGED, false);
        this.entityData.define(POOP, false);
        this.entityData.define(POOP_TIME, 0);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setDamaged(boolean damaged) {
        this.entityData.set(DAMAGED, damaged);
    }
    public boolean isDamaged() {
        return this.entityData.get(DAMAGED);
    }

    public void setPoop(boolean poop) {
        this.entityData.set(POOP, poop);
    }
    public boolean isPoop() {
        return this.entityData.get(POOP);
    }

    public void setPoopTime(int poopTime) {
        this.entityData.set(POOP_TIME, poopTime);
    }
    public int getPoopTime() {
        return this.entityData.get(POOP_TIME);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new HorseAttackGoal(this, 1.2f, true));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0f, DefaultOreHorse.class));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2f, Ingredient.of(Items.APPLE, Items.GOLDEN_CARROT), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0f));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7f));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20f)
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
                .add(Attributes.JUMP_STRENGTH, 2f)
                .add(Attributes.FOLLOW_RANGE, 12f)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5f)
                .add(Attributes.ATTACK_DAMAGE, 4f);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.DEFAULT_ORE_HORSE.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HORSE_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.HORSE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HORSE_DEATH;
    }
}
