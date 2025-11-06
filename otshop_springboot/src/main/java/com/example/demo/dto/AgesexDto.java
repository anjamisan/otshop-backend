package com.example.demo.dto;

import model.Agesex;

public class AgesexDto {
	
	private int idAgeSex;

	private String ageSexGroup;

	public AgesexDto() {
		
	}

	public AgesexDto(int idAgeSex, String ageSexGroup) {
		super();
		this.idAgeSex = idAgeSex;
		this.ageSexGroup = ageSexGroup;
	}

	public int getIdAgeSex() {
		return idAgeSex;
	}

	public void setIdAgeSex(int idAgeSex) {
		this.idAgeSex = idAgeSex;
	}

	public String getAgeSexGroup() {
		return ageSexGroup;
	}

	public void setAgeSexGroup(String ageSexGroup) {
		this.ageSexGroup = ageSexGroup;
	}
	
	public static AgesexDto fromEntity(Agesex agesex) {
        if (agesex == null) return null;

        return new AgesexDto(
            agesex.getIdAgeSex(),
            agesex.getAgeSexGroup()
        );
    }

}
