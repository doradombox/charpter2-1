package priv.scor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import priv.scor.entity.GetCountryRequest;
import priv.scor.entity.GetCountryResponse;
import priv.scor.repository.CountryRepository;

/**
 * @createBy Edgar
 * @date 2018/5/30
 * @Package_name priv.scor.controller
 */
@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.yourcompany.com/webservice";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

}
