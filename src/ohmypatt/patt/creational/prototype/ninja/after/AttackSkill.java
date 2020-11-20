package ohmypatt.patt.creational.prototype.ninja.after;

public class AttackSkill implements Cloneable {
	private String skillName;
	private int skillLevel, baseDamage, stagedDamage;
	private static final float GENERIC_STAGED_DAMAGE_MULTIPLIER = 0.2f;

	public AttackSkill(String skillName, int skillLevel, int baseDamage) {
		this(skillName, skillLevel, baseDamage, GENERIC_STAGED_DAMAGE_MULTIPLIER);
	}

	public AttackSkill(String skillName, int skillLevel, int baseDamage, float damageMultiplier) {
		this(skillName, skillLevel, baseDamage, Math.round(baseDamage * damageMultiplier));
	}

	public AttackSkill(String skillName, int skillLevel, int baseDamage, int stagedDamage) {
		this.skillName = skillName;
		this.skillLevel = skillLevel;
		this.baseDamage = baseDamage;
		this.stagedDamage = stagedDamage;
	}

	private int getMultiplierDamage() {
		return skillLevel * stagedDamage;
	}

	public int applyDamage() {
		return baseDamage + getMultiplierDamage();
	}

	public String getSkillName() {
		return skillName;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setStagedDamage(int stagedDamage) {
		this.stagedDamage = stagedDamage;
	}

	public int getStagedDamage() {
		return stagedDamage;
	}

	public void setOverallDamage(int baseDamage) {
		setOverallDamage(baseDamage, GENERIC_STAGED_DAMAGE_MULTIPLIER);
	}

	public void setOverallDamage(int baseDamage, float damageMultiplier) {
		setOverallDamage(baseDamage, Math.round(baseDamage * damageMultiplier));
	}

	public void setOverallDamage(int baseDamage, int stagedDamage) {
		setBaseDamage(baseDamage);
		setStagedDamage(stagedDamage);
	}

	@Override
	public String toString() {
		return String.format("%s (level %d)", getSkillName(), getSkillLevel());
	}

	@Override
	protected AttackSkill clone() throws CloneNotSupportedException {
		try {
			return (AttackSkill) super.clone();			
		} catch (CloneNotSupportedException ex) {
			return new AttackSkill(skillName, skillLevel, baseDamage, stagedDamage);
		}
	}
}