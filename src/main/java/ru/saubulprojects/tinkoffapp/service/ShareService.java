package ru.saubulprojects.tinkoffapp.service;

import ru.saubulprojects.tinkoffapp.model.Share;

public interface ShareService {

	Share findByTicker(String ticker);

}
