package com.example.test.application.excepcion;

import com.example.test.application.SecurityManagementService;
import com.example.test.domain.models.Customer;
import com.example.test.domain.models.Person;
import com.example.test.domain.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PersonFieldsBlockerService {

    private SecurityManagementService securityService;

    public List<?> block(final List<?> items,final String token) {
        if (!CollectionUtils.isEmpty(items)) {
            final User user = securityService.findCurrentUser(token);
            return items.stream()
                    .map(item -> block(user, item))
                    .collect(Collectors.toList());
        }
        return items;
    }

    public Object block(final Object item,final String token) {
        final User user = securityService.findCurrentUser(token);
        return block(user, item);
    }

    public Object block(final User user, final Object item) {
        if (item instanceof Customer && Objects.nonNull(((Customer) item).getPerson())) {
            return ((Customer) item).withPerson(block(((Customer) item).getPerson(), user, item.getClass()));
        }
        return item;
    }

    private Person block(final Person person, final User user, final Class<?> clazz) {
        return person
                .withName(person.getName())
                .withSurname(person.getSurname());
    }


}
