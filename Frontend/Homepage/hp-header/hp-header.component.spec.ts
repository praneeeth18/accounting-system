import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HpHeaderComponent } from './hp-header.component';

describe('HpHeaderComponent', () => {
  let component: HpHeaderComponent;
  let fixture: ComponentFixture<HpHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HpHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HpHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
