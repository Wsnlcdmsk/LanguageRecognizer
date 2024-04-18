package com.project.langrecognizer.service;

import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.model.Tag;

import java.util.List;

/**
 * Сервис для работы с метками (тегами).
 */
public interface TagService {

    /**
     * Сохраняет метку в базу данных.
     *
     * @param tag Метка для сохранения.
     * @return DTO сохраненной метки.
     */
    TagDTO saveTag(Tag tag);

    /**
     * Сохраняет список меток в базу данных.
     *
     * @param tags Список меток для сохранения.
     * @return Список DTO сохраненных меток.
     */
    List<TagDTO> saveTags(List<Tag> tags);

    /**
     * Получает все метки из базы данных.
     *
     * @return Список DTO всех меток.
     */
    List<TagDTO> getTags();

    /**
     * Получает метку по её идентификатору.
     *
     * @param id Идентификатор метки.
     * @return DTO найденной метки или null, если метка не найдена.
     */
    TagDTO getTagById(Long id);

    /**
     * Получает метку по её имени.
     *
     * @param name Имя метки.
     * @return DTO найденной метки или null, если метка не найдена.
     */
    TagDTO getTagByName(String name);

    /**
     * Удаляет метку из базы данных по её идентификатору.
     *
     * @param id Идентификатор метки для удаления.
     * @return Строка с сообщением об успешном удалении или ошибке, если метка не найдена.
     */
    String deleteTag(Long id);

    /**
     * Обновляет информацию о метке в базе данных.
     *
     * @param tag Обновленная информация о метке.
     * @return DTO обновленной метки или null, если метка не найдена.
     */
    TagDTO updateTag(Tag tag);
}
