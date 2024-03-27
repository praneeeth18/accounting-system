import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchasestableComponent } from './purchasestable.component';

describe('PurchasestableComponent', () => {
  let component: PurchasestableComponent;
  let fixture: ComponentFixture<PurchasestableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PurchasestableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PurchasestableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
