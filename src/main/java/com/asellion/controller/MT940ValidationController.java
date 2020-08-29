package com.asellion.controller;

import com.asellion.domain.InvalidRecord;
import com.asellion.service.MT940FileValidatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
//=@Api(value="/",description="Validate the MT940 record",produces ="application/json")
//@RequestMapping("/")
public class MT940ValidationController {

    @Autowired
    MT940FileValidatorService mt940FileValidatorService;

    Logger logger = LoggerFactory.getLogger("MT940ValidationController");

    /**
     * Controller to display the Default page to load the MT940 Validation page.
     * @param model
     * @return
     */
    @ApiOperation(value="Display MT940 Validatory",response=String.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="MT940 Validation Response",response=String.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="File not found"),
            @ApiResponse(code=400,message="Bad Request")
    })
    @GetMapping("/")
    public String loadMT940Validation(Model model) {
        return "MT940Validation";
    }

    /**
     *
     * @param file
     * @param model
     * @return
     * @throws Exception
     */
    @ApiOperation(value="Validate the MT940 input file and return the report of the failure message",response=InvalidRecord.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="MT940 Validation Response",response=InvalidRecord.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="File not found"),
            @ApiResponse(code=400,message="Bad Request")
    })
    @PostMapping("/")
    public String mt940Validation(@RequestParam("file") MultipartFile file,Model model) throws Exception{

        logger.info("Content Type: "+file.getContentType());
        logger.info("FileSize is: "+file.getSize());
        logger.info("Filename :"+file.getOriginalFilename());

        List<InvalidRecord> lstInvalidRecords = mt940FileValidatorService.validateMT940File(file);
        model.addAttribute("invalidRecords",lstInvalidRecords);
        return "MT940Validation";
    }
}
