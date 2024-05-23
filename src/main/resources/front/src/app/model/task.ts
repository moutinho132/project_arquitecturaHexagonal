
export class Task {
    id: string;
    description: string = '';
    state: string ="";
    customer: {
        id: number;
        person: {
            id: number;
            name: string;
            surname: string;
            dateOfBirth: string;
        };
        creationTime: string;
    };

    constructor() {
        this.id = "";
        this.customer = {
            id: 0,
            person: {
                id: 0,
                name: "",
                surname: "",
                dateOfBirth: ""
            },
            creationTime: ""
        };
    }
}
