import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LedgertableComponent } from './ledgertable.component';

describe('LedgertableComponent', () => {
  let component: LedgertableComponent;
  let fixture: ComponentFixture<LedgertableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LedgertableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LedgertableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
