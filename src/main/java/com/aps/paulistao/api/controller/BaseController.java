package com.aps.paulistao.api.controller;

import com.aps.paulistao.api.dto.BaseDTO;
import com.aps.paulistao.api.dto.EquipeResponseDTO;
import com.aps.paulistao.api.entity.BaseEntity;
import com.aps.paulistao.api.exception.StandardError;
import com.aps.paulistao.api.mapper.GenericMapper;
import com.aps.paulistao.api.service.BaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@NoArgsConstructor
public abstract class BaseController<T extends BaseEntity, D extends BaseDTO> {
    @Autowired
    private BaseService<T> service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Getter
    private GenericMapper<T, D> mapper;

    @Setter
    private BaseDTO dtoClass;

    public BaseController(final GenericMapper<T, D> mapper) {
        this.mapper = mapper;
    }


    @ApiOperation("Busca de registro por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Class.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.toDTO(service.findById(id)));
    }

    @ApiOperation("Listagem de registros")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EquipeResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @GetMapping
    public ResponseEntity<D> find(){
        //return ResponseEntity.ok().body(service.find());
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation("Inclusão de registro")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Class.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @PostMapping
    public ResponseEntity<D> save(@RequestBody @Valid D dto, HttpServletResponse response){
        final T entity = service.save(mapper.toEntity(dto));
        publisher.publishEvent(new CreateResourceEvent(this, entity.getId(), response));
        final String uri = response.getHeader("Location");
        return ResponseEntity.created(URI.create(uri)).body(mapper.toDTO(entity));
    }

    @ApiOperation("Alteração de registro")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Updated", response = Void.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = StandardError.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid D dto){
        service.update(id, mapper.toEntity(dto));
        return ResponseEntity.noContent().build();
    }
}