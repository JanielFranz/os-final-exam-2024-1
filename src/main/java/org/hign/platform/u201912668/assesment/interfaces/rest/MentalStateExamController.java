package org.hign.platform.u201912668.assesment.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hign.platform.u201912668.assesment.domain.model.queries.GetMentalStateExamByIdQuery;
import org.hign.platform.u201912668.assesment.domain.services.MentalStateExamCommandService;
import org.hign.platform.u201912668.assesment.domain.services.MentalStateExamQueryService;
import org.hign.platform.u201912668.assesment.interfaces.rest.resources.CreateMentalStateExamResource;
import org.hign.platform.u201912668.assesment.interfaces.rest.resources.MentalStateExamResource;
import org.hign.platform.u201912668.assesment.interfaces.rest.transform.CreateMentalStateExamCommandFromResourceAssembler;
import org.hign.platform.u201912668.assesment.interfaces.rest.transform.MentalStateExamResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mental-state-exams")
@Tag(name = "Mental State Exam", description = "Mental State Exam related endpoints")
public class MentalStateExamController {
    private final MentalStateExamCommandService mentalStateExamCommandService;
    private final MentalStateExamQueryService mentalStateExamQueryService;

    public MentalStateExamController(MentalStateExamCommandService mentalStateExamCommandService, MentalStateExamQueryService mentalStateExamQueryService) {
        this.mentalStateExamCommandService = mentalStateExamCommandService;
        this.mentalStateExamQueryService = mentalStateExamQueryService;
    }

    @Operation(summary = "Create a new mental state exam")
    @ApiResponses(value =
            {
                    @ApiResponse(responseCode = "201", description = "Mental state exam created"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @PostMapping
    public ResponseEntity<MentalStateExamResource> createMentalStateExam(@RequestBody CreateMentalStateExamResource createMentalStateExamResource) {
        var createMentalStateExamCommand =
                CreateMentalStateExamCommandFromResourceAssembler.toCommandFromResource(createMentalStateExamResource);
        var mentalStateExamId = mentalStateExamCommandService.handle(createMentalStateExamCommand);
        if(mentalStateExamId == null) return ResponseEntity.badRequest().build();

        var getMentalStateExamByIdQuery = new GetMentalStateExamByIdQuery(mentalStateExamId);
        var mentalStateExam = mentalStateExamQueryService.handle(getMentalStateExamByIdQuery);
        if(mentalStateExam.isEmpty()) return ResponseEntity.badRequest().build();

        var mentalStateExamEntity = mentalStateExam.get();

        var mentalStateExamResource = MentalStateExamResourceFromEntityAssembler.toResourceFromEntity(mentalStateExamEntity);
        return new ResponseEntity<>(mentalStateExamResource, HttpStatus.CREATED);

    }
}
