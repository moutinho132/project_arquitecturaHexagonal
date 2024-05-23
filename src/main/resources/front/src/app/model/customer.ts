export class Person {
  id: number;
  name: string;
  surname: string;
  dni: string;
  dateOfBirth: string;

  constructor() {
    this.id = 0;
    this.name = '';
    this.surname = '';
    this.dni = '';
    this.dateOfBirth = '';
  }
}

export class Customer {
  id: number;
  person: Person;
  reference: string;

  constructor() {
    this.id = 0;
    this.person = new Person();
    this.reference = '';
  }
}
