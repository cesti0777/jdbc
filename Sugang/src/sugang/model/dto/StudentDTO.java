package sugang.model.dto;

public class StudentDTO extends PersonDTO {
	private String stuId;

	public StudentDTO() {
		super();
	}

	public StudentDTO(String mcode, String name, String code, String stuId) {
		super(mcode, name, code);
		this.stuId = stuId;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StudentDTO [stuId=");
		builder.append(stuId);
		builder.append("]");
		return builder.toString();
	}

}
