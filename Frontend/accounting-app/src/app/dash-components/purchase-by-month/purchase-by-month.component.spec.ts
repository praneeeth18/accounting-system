import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseByMonthComponent } from './purchase-by-month.component';

describe('PurchaseByMonthComponent', () => {
  let component: PurchaseByMonthComponent;
  let fixture: ComponentFixture<PurchaseByMonthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PurchaseByMonthComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PurchaseByMonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
