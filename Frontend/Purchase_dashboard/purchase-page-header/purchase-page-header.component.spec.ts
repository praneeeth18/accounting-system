import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchasePageHeaderComponent } from './purchase-page-header.component';

describe('PurchasePageHeaderComponent', () => {
  let component: PurchasePageHeaderComponent;
  let fixture: ComponentFixture<PurchasePageHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PurchasePageHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PurchasePageHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
