package sugang.model.dto;

public class ProfessorDTO extends PersonDTO {
	private String proClass;

	public ProfessorDTO() {
		super();
	}

	public ProfessorDTO(String mcode, String name, String code, String proClass) {
		super(mcode, name, code);
		this.proClass = proClass;
	}

	public String getProClass() {
		return proClass;
	}

	public void setProClass(String proClass) {
		this.proClass = proClass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProfessorDTO [proClass=");
		builder.append(proClass);
		builder.append("]");
		return builder.toString();
	}
}
