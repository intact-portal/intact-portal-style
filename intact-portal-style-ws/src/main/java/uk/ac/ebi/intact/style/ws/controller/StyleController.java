package uk.ac.ebi.intact.style.ws.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.intact.style.model.legend.NetworkLegend;
import uk.ac.ebi.intact.style.model.shapes.EdgeShape;
import uk.ac.ebi.intact.style.model.shapes.NodeShape;
import uk.ac.ebi.intact.style.service.StyleService;
import uk.ac.ebi.intact.style.ws.controller.model.NetworkStats;
import uk.ac.ebi.intact.style.ws.controller.model.edge.*;
import uk.ac.ebi.intact.style.ws.controller.model.node.InteractorProperties;
import uk.ac.ebi.intact.style.ws.controller.model.node.NodeStyle;

import java.awt.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class StyleController {
    private static final Log log = LogFactory.getLog(StyleController.class);

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @ApiOperation(value = "", tags = {"legend"})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/legend",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<NetworkLegend> legend(@RequestBody NetworkStats stats) {
        return new ResponseEntity<>(
                styleService.createLegend(
                        stats.getTaxIds(),
                        stats.getNodeTypes(),
                        stats.isNodeMutated(),
                        stats.getEdgeTypes(),
                        stats.isEdgeExpanded(),
                        stats.isEdgeAffectedByMutation()),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "", tags = {"interactor"})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interactor",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<NodeStyle> styleInteractor(@RequestBody InteractorProperties properties) {
        NodeStyle style = new NodeStyle();
        style.setFill(styleService.getInteractorColor(properties.getTaxId()));
        style.setStroke(styleService.getInteractorBorderColor(properties.isMutated()));
        style.setStrokeWidth(styleService.getInteractorBorderWidth(properties.isMutated()));
        style.setShape(styleService.getInteractorShape(properties.getTypeMIId()));
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interactor"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interactor/fill",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Color> getInteractorFillColor(@RequestParam(defaultValue = "9606") String taxId) {
        return new ResponseEntity<>(styleService.getInteractorColor(taxId), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interactor"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interactor/stroke",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Color> getInteractorStrokeColor(@RequestParam boolean isMutated) {
        return new ResponseEntity<>(styleService.getInteractorBorderColor(isMutated), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interactor"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interactor/stroke-width",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> getInteractorStrokeWidth(@RequestParam boolean isMutated) {
        return new ResponseEntity<>(styleService.getInteractorBorderWidth(isMutated), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interactor"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interactor/shape",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<NodeShape> getInteractorShape(@RequestParam(defaultValue = "MI:0326") String typeMIId) {
        return new ResponseEntity<>(styleService.getInteractorShape(typeMIId), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interaction",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<GlobalEdgeStyle> styleInteraction(@RequestBody InteractionProperties properties) {
        GlobalEdgeStyle style = new GlobalEdgeStyle();

        style.setSummaryColor(styleService.getSummaryInteractionColor(properties.getMiScore()));
        style.setSummaryWidth(styleService.getSummaryInteractionWidth(properties.getNbSummarisedInteractions()));

        style.setEvidenceColor(styleService.getInteractionColor(properties.getTypeMIId()));
        style.setShape(styleService.getInteractionShape(properties.isExpanded()));

        style.setMutationColor(styleService.getMutationInteractionColor(properties.isMutated()));
        style.setMutationWidth(styleService.getMutationInteractionWidth(properties.isMutated()));
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interaction/summary",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<EdgeStyle> styleSummaryInteraction(@RequestBody InteractionSummaryProperties properties) {
        EdgeStyle style = new EdgeStyle();
        style.setColor(styleService.getSummaryInteractionColor(properties.getMiScore()));
        style.setWidth(styleService.getSummaryInteractionWidth(properties.getNbSummarisedInteractions()));
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interaction/summary/color",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Color> getSummaryInteractionColor(@RequestParam("mi_score") double miScore) {
        return new ResponseEntity<>(styleService.getSummaryInteractionColor(miScore), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interaction/summary/width",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> getSummaryInteractionWidth(@RequestParam(value = "nb_summarised_interactions", defaultValue = "1") int nbSummarisedInteractions) {
        return new ResponseEntity<>(styleService.getSummaryInteractionWidth(nbSummarisedInteractions), HttpStatus.OK);
    }


    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interaction/evidence",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<EdgeStyle> styleEvidenceInteraction(@RequestBody InteractionEvidenceProperties properties) {
        EdgeStyle style = new EdgeStyle();
        style.setColor(styleService.getInteractionColor(properties.getTypeMIId()));
        style.setShape(styleService.getInteractionShape(properties.isExpanded()));
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interaction/shape",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<EdgeShape> getInteractionShape(@RequestParam boolean expanded) {
        return new ResponseEntity<>(styleService.getInteractionShape(expanded), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interaction/evidence/color",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Color> getEvidenceInteractionColor(@RequestParam(value = "type_id", defaultValue = "MI:0403") String typeMIId) {
        return new ResponseEntity<>(styleService.getInteractionColor(typeMIId), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interaction/mutation",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<EdgeStyle> styleMutationInteraction(@RequestBody InteractionMutationProperties properties) {
        EdgeStyle style = new EdgeStyle();
        style.setColor(styleService.getMutationInteractionColor(properties.hasMutation()));
        style.setWidth(styleService.getMutationInteractionWidth(properties.hasMutation()));
        style.setShape(styleService.getInteractionShape(properties.isExpanded()));
        return new ResponseEntity<>(style, HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interaction/mutation/color",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Color> getMutationInteractionColor(@RequestParam("has_mutation") boolean hasMutation) {
        return new ResponseEntity<>(styleService.getMutationInteractionColor(hasMutation), HttpStatus.OK);
    }

    @ApiOperation(value = "", tags = {"interaction"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/interaction/mutation/width",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Integer> getMutationInteractionWidth(@RequestParam("has_mutation") boolean hasMutation) {
        return new ResponseEntity<>(styleService.getMutationInteractionWidth(hasMutation), HttpStatus.OK);
    }

    /* Updates */

    @ApiOperation(value = "", tags = {"update"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/update-mi-ontology",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateMIOntology(@RequestParam String password) {
        if (password.equals("EliotIsTheVeryBest")) {
            styleService.updateMIOntology();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @ApiOperation(value = "", tags = {"update"})
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/update-taxonomy",
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> updateTaxonomy(@RequestParam String password) {
        if (password.equals("EliotIsTheVeryBest")) {
            styleService.updateTaxonomy();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}
