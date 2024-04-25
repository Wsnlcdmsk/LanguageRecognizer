package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TextDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.model.Text;

import java.util.List;

/**
 * Сервис для работы с текстами.
 */
public interface TextService {

    /**
     * Сохраняет текст в базу данных.
     *
     * @param text Текст для сохранения.
     * @return DTO сохраненного текста.
     */
    TextDTO saveText(Text text);

    /**
     * Сохраняет список текстов в базу данных.
     *
     * @param texts Список текстов для сохранения.
     * @return Список DTO сохраненных текстов.
     */
    List<TextDTO> saveTexts(List<Text> texts);

    /**
     * Получает все тексты из базы данных.
     *
     * @return Список DTO всех текстов.
     */
    List<TextDTO> getTexts();

    /**
     * Получает текст по его идентификатору.
     *
     * @param id Идентификатор текста.
     * @return DTO найденного текста или null, если текст не найден.
     */
    TextDTO getTextById(Long id);

    /**
     * Получает текст по его содержимому.
     *
     * @param content Содержимое текста.
     * @return DTO найденного текста или null, если текст не найден.
     */
    TextDTO getTextByContent(String content);

    /**
     * Удаляет текст из базы данных по его идентификатору.
     *
     * @param id Идентификатор текста для удаления.
     * @return Строка с сообщением об успешном удалении или ошибке,
     * если текст не найден.
     */
    String deleteText(Long id);

    /**
     * Обновляет информацию о тексте в базе данных.
     *
     * @param text Обновленная информация о тексте.
     * @return DTO обновленного текста или null, если текст не найден.
     */
    TextDTO updateText(Text text);

    /**
     * Находит тексты в базе данных, отсортированные по метке.
     *
     * @param tag Метка, по которой производится поиск текстов.
     * @return Список строк содержимого текстов, отсортированных по метке.
     */
    List<String> findTextsSortedByTag(String tag);

    /**
     * Находит тексты в базе данных, отсортированные по языку.
     *
     * @param language Язык, по которому производится поиск текстов.
     * @return Список строк содержимого текстов, отсортированных по языку.
     */
    List<String> findTextsSortedByLanguage(String language);

    /**
     * Добавляет список текстов к языку.
     *
     * @param texts Список текстов для добавления.
     * @param id Идентификатор языка.
     * @return Сообщение об ошибке или null, если операция выполнена успешно.
     */
    String addListOfTextToLanguage(List<Text> texts, Long id);
}
