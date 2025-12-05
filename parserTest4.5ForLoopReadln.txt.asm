# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varx: .word 0
	vary: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#readln
	li $v0, 5
	syscall
	la $t0 varx
	sw $v0 ($t0)
	#writeln
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#for loop
	#number
	li $v0, 1
	la $t0 vary
	sw $v0 ($t0)
startfor1:
	la $t0 vary
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 12
	lw $t0 ($sp)
	addu $sp $sp 4
	bgt $t0 $v0 endfor1
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	la $t0 vary
	lw $v0 ($t0)
	addi $v0 $v0 1
	la $t0 vary
	sw $v0 ($t0)
	j startfor1
endfor1:
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#while loop
startwhile2:
	#condition
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 75
	lw $t0 ($sp)
	addu $sp $sp 4
	ble $t0 $v0 endwhile2
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 2
	lw $t0 ($sp)
	addu $sp $sp 4
	subu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	j startwhile2
endwhile2:
	li $v0, 10
	syscall
	
