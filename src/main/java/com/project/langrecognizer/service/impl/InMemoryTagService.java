/**
 * The InMemoryTagService class provides implementation
 * for TagService interface using in-memory storage.
 */
package com.project.langrecognizer.service.impl;

import com.project.langrecognizer.aspect.LoggingAnnotation;
import com.project.langrecognizer.aspect.RequestCounterAnnotation;
import com.project.langrecognizer.dto.TagDTO;
import com.project.langrecognizer.exception.BadRequestException;
import com.project.langrecognizer.exception.ResourceNotFoundException;
import com.project.langrecognizer.mapper.TagMapper;
import com.project.langrecognizer.model.Tag;
import com.project.langrecognizer.repository.TagRepository;
import com.project.langrecognizer.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryTagService implements TagService {

    /** The repository for accessing the database
     * and performing CRUD operations on the Tag entity. */
    private TagRepository repository;

    /** The mapper for converting between
     * Tag entities and DTOs. */
    private TagMapper mapper;

    /** The error message to be thrown when no tag
     * is found with the given name. */
    private static final String NO_TAG_EXIST_WITH_NAME =
            "No tag found with name: ";

    /** The error message to be thrown when no tag
     * is found with the given id. */
    private static final String NO_TAG_EXIST_WITH_ID =
            "No tag found with id: ";

    /** The error message to be thrown when no name
     * is provided for a new tag. */
    private static final String NO_NAME_PROVIDED =
            "No name provided";

    /** The error message to be thrown when no tags
     * are provided for an update. */
    private static final String NO_TAGS_PROVIDED =
            "No tags provided";


    /**
     * Saves a tag entity.
     *
     * @param tag The tag entity to be saved.
     * @return The saved tag DTO.
     * @throws BadRequestException If no name is provided for the tag.
     */
    @Override
    @LoggingAnnotation
    public TagDTO saveTag(final Tag tag) throws BadRequestException {
        if (tag.getName() == null) {
            throw new BadRequestException(NO_NAME_PROVIDED);
        }
        repository.save(tag);
        return mapper.toDTO(tag);
    }

    /**
     * Saves a list of tag entities.
     *
     * @param tags The list of tag entities to be saved.
     * @return The list of saved tag DTOs.
     * @throws BadRequestException If no tags are provided
     * or if no name is provided for any tag.
     */
    @Override
    @LoggingAnnotation
    public List<TagDTO> saveTags(final List<Tag> tags)
            throws BadRequestException {
        if (tags.isEmpty()) {
            throw new BadRequestException(NO_TAGS_PROVIDED);
        }
        for (Tag tag : tags) {
            if (tag.getName() == null) {
                throw new BadRequestException(NO_NAME_PROVIDED);
            }
        }
        repository.saveAll(tags);
        return mapper.toDTOs(tags);
    }

    /**
     * Retrieves all tags.
     *
     * @return A list of tag DTOs.
     */
    @Override
    @RequestCounterAnnotation
    public List<TagDTO> getTags() {
        return mapper.toDTOs(repository.findAll());
    }

    /**
     * Retrieves a tag by its ID.
     *
     * @param id The ID of the tag to retrieve.
     * @return The tag DTO.
     * @throws ResourceNotFoundException If no tag is found with the given ID.
     */
    @Override
    public TagDTO getTagById(final Long id) throws ResourceNotFoundException {
        return mapper.toDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_TAG_EXIST_WITH_ID + id)));
    }

    /**
     * Retrieves a tag by its name.
     *
     * @param name The name of the tag to retrieve.
     * @return The tag DTO.
     * @throws ResourceNotFoundException If no tag is found with the given name.
     */
    @Override
    public TagDTO getTagByName(final String name)
            throws ResourceNotFoundException {
        return mapper.toDTO(repository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_TAG_EXIST_WITH_NAME + name)));
    }

    /**
     * Deletes a tag by its ID.
     *
     * @param id The ID of the tag to delete.
     * @return A message indicating the deletion.
     * @throws ResourceNotFoundException If no tag is found with the given ID.
     */
    @Override
    @LoggingAnnotation
    public String deleteTag(final Long id) throws ResourceNotFoundException {
        Tag tag = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        NO_TAG_EXIST_WITH_ID + id));
        repository.deleteById(id);
        return "tag removed!! " + id;
    }

    /**
     * Updates a tag.
     *
     * @param tag The tag entity with updated information.
     * @return The updated tag DTO.
     * @throws BadRequestException If no name is provided for the tag.
     */
    @Override
    @LoggingAnnotation
    public TagDTO updateTag(final Tag tag) throws BadRequestException {
        if (tag.getName() == null) {
            throw new BadRequestException(NO_NAME_PROVIDED);
        }
        Tag existingTag;
        try {
            existingTag = repository.findById(tag.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            NO_TAG_EXIST_WITH_ID + tag.getId()));
        } catch (ResourceNotFoundException exception) {
            existingTag = new Tag();
            existingTag.setId(tag.getId());
        }
        existingTag.setName(tag.getName());
        existingTag.setTexts(tag.getTexts());
        repository.deleteById(tag.getId());
        repository.save(existingTag);
        return mapper.toDTO(tag);
    }
}
