describe("Register a donor", ()=>{
    it("loads donor register form", ()=>{
        cy.visit("http://127.0.0.1:5173/");
        cy.get('[data-cy="submit"]').click();
    });
    it("should redirect to a success page after filling the form and clicking submit", ()=>{
        cy.get("#firstName").type("Kanjye");
        cy.get("#lastName").type("Keza");
        cy.get("#email").type("keza@gmail.com");
        cy.get("#num_of_packets").type(2);
        cy.get("#blood_type").type("A+");
        cy.get(".my-4").click();
    })
})