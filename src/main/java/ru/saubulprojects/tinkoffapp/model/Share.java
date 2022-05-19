package ru.saubulprojects.tinkoffapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Share {
	private String name;
	private String ticker;
	private String figi;
	private String classCode;
}
