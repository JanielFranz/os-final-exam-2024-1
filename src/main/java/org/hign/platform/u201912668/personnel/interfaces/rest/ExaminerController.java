package org.hign.platform.u201912668.personnel.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hign.platform.u201912668.personnel.domain.model.queries.GetExaminerByIdQuery;
import org.hign.platform.u201912668.personnel.domain.services.ExaminerCommandService;
import org.hign.platform.u201912668.personnel.domain.services.ExaminerQueryService;
import org.hign.platform.u201912668.personnel.interfaces.rest.resources.CreateExaminerResource;
import org.hign.platform.u201912668.personnel.interfaces.rest.resources.ExaminerResource;
import org.hign.platform.u201912668.personnel.interfaces.rest.transform.CreateExaminerCommandFromResourceAssembler;
import org.hign.platform.u201912668.personnel.interfaces.rest.transform.ExaminerResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/examiners")
@Tag(name="Examiners", description = "All examiner related operations")
public class ExaminerController {
    private final ExaminerCommandService examinerCommandService;
    private final ExaminerQueryService examinerQueryService;

    public ExaminerController(ExaminerCommandService examinerCommandService, ExaminerQueryService examinerQueryService) {
        this.examinerCommandService = examinerCommandService;
        this.examinerQueryService = examinerQueryService;
    }

    @Operation(summary = "Create a new examiner")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Examiner created"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Examiner already exists")
    })
    @PostMapping
    public ResponseEntity<ExaminerResource> createExaminer(@RequestBody CreateExaminerResource createExaminerResource) {
        var createExaminerCommand = CreateExaminerCommandFromResourceAssembler.toCommandFromResource(createExaminerResource);
        var examinerId = examinerCommandService.handle(createExaminerCommand);
        if(examinerId == null || examinerId == 0L) return ResponseEntity.badRequest().build();

        var getExaminerByIdQuery = new GetExaminerByIdQuery(examinerId);
        var examiner = examinerQueryService.handle(getExaminerByIdQuery);
        if(examiner.isEmpty()) return ResponseEntity.notFound().build();

        var examinerEntity = examiner.get();
        var examinerResource = ExaminerResourceFromEntityAssembler.toResourceFromEntity(examinerEntity);
        return new ResponseEntity<>(examinerResource, HttpStatus.CREATED);

    }
}
