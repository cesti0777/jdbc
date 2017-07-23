package sugang.model.dto;

public class PersonDTO {
	private String mcode;
	private String name;
	private String code;

	public PersonDTO() {
	}

	public PersonDTO(String mcode, String name, String code) {
		this.mcode = mcode;
		this.name = name;
		this.code = code;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonDTO [mcode=");
		builder.append(mcode);
		builder.append(", name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
}
