package com.example.test.infrastructure.mapper;

import com.example.test.domain.models.Customer;
import com.example.test.infrastructure.request.CustomerRequest;
import com.example.test.infrastructure.response.CustomerResponse;
import org.mapstruct.Mapper;
import java.util.List;

/*
*
* @Mapper(
    componentModel = "spring",
    uses = {
        CustomerStatusApiMapper.class,
        MembershipApiMapper.class,
        CompanyApiMapper.class,
        PersonApiMapper.class,
        LeadApiMapper.class,
        CustomerStatusApiMapper.class,
        UserApiMapper.class,
        CreationTypeApiMapper.class,
        PaymentDetailsApiMapper.class
    }
)*/
@Mapper(
        componentModel = "spring",
        uses = {
                PersonApiMapper.class,
                UserApiMapper.class,
        }

)
public interface CustomerApiMapper {
    Customer requestToModel(CustomerRequest request);
    List<Customer> requestToModelList(List<CustomerRequest> list);

    CustomerResponse modelToResponse(Customer model);

    List<CustomerResponse> modelsToResponseList(List<Customer> list);
}
